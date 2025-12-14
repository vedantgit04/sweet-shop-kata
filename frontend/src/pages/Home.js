import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';
import authService from '../services/authService';

// --- IMPORT YOUR LOCAL IMAGES HERE ---
import jalebiImg from '../assets/jalebi.jpg';
import sonpapdiImg from '../assets/sonpapdi.jpg';
import gulabJamunImg from '../assets/gulabjamun.jpg';
import kajuKatliImg from '../assets/kajukatli.jpg'; 
import defaultImg from '../assets/premium-bg.jpg'; 

const Home = () => {
    const [sweets, setSweets] = useState([]);
    const [keyword, setKeyword] = useState('');
    
    // States
    const [activeSweet, setActiveSweet] = useState(null); 
    const [modalType, setModalType] = useState(null); 
    const [quantityInput, setQuantityInput] = useState(1);
    
    // Notification State
    const [notification, setNotification] = useState({ show: false, title: '', message: '', type: 'success' });

    // Admin Add Form
    const [newSweet, setNewSweet] = useState({ name: '', category: '', price: '', quantity: '' });
    const [showAddForm, setShowAddForm] = useState(false);

    const navigate = useNavigate();
    const user = authService.getCurrentUser();
    const isAdmin = authService.isAdmin();

    const fetchSweets = async (searchKeyword = '') => {
        try {
            const endpoint = searchKeyword ? `/sweets/search?keyword=${searchKeyword}` : '/sweets';
            const response = await api.get(endpoint);
            setSweets(response.data);
        } catch (err) {
            if (err.response?.status === 401 || err.response?.status === 403) {
                authService.logout();
                navigate('/login');
            }
        }
    };

    useEffect(() => { fetchSweets(); }, [navigate]);

    // --- IMAGE HELPER ---
    const getSweetImage = (sweetName) => {
        const cleanName = sweetName.toLowerCase().replace(/\s/g, '');
        if (cleanName.includes('jalebi')) return jalebiImg;
        if (cleanName.includes('sonpapdi') || cleanName.includes('soan')) return sonpapdiImg;
        if (cleanName.includes('gulab')) return gulabJamunImg;
        if (cleanName.includes('kaju')) return kajuKatliImg;
        return defaultImg;
    };

    // --- Helpers & Actions ---

    // 1. Shows the Success/Error Popup
    const showNotification = (title, message, type = 'success') => {
        setNotification({ show: true, title, message, type });
    };

    // 2. Closes ONLY the Input Modal (Quantity Box)
    const closeInputModal = () => {
        setActiveSweet(null);
        setModalType(null);
    };

    // 3. Closes EVERYTHING (Used by the "Okay" button)
    const closeAll = () => {
        closeInputModal();
        setNotification({ ...notification, show: false });
    };

    const sortSweets = (direction) => {
        const sorted = [...sweets].sort((a, b) => direction === 'asc' ? a.price - b.price : b.price - a.price);
        setSweets(sorted);
    };

      const successMessages = [
        "Excellent choice! You've just added a little more sweetness to your life. 🍬",
        "A box of happiness is yours! May every bite bring you joy. ✨",
        "Pure delight is confirmed! Get ready to indulge in the royal taste of India. 🪔",
        "Your taste buds will thank you! This is a truly delicious decision. 💖",
        "Sweetness overload! You absolutely deserve this treat today. 🎉",
        "Tradition meets taste! We hope this brings a smile to your face. 😊"
    ];

    const confirmAction = async () => {
        if (!activeSweet) return;
        
        // 1. Close the input modal immediately
        closeInputModal();

        try {
            if (modalType === 'BUY') {
                await api.post(`/sweets/${activeSweet.id}/purchase`, parseInt(quantityInput));
                
                // Pick a random beautiful message
                const randomMsg = successMessages[Math.floor(Math.random() * successMessages.length)];
                
                // 2. Show Premium Success Notification
                showNotification("Sweetness Confirmed! 🎉", randomMsg);
                
            } else if (modalType === 'RESTOCK') {
                await api.post(`/sweets/${activeSweet.id}/restock`, parseInt(quantityInput));
                showNotification("Inventory Updated 📦", "Stock has been replenished successfully.");
            } else if (modalType === 'DELETE') {
                await api.delete(`/sweets/${activeSweet.id}`);
                showNotification("Item Removed 🗑️", `${activeSweet.name} has been deleted from the menu.`);
            }
            fetchSweets(keyword); 
        } catch (err) {
            showNotification("Action Failed ❌", err.response?.data || "Something went wrong.", "error");
        }
    
    };

    const handleAddSweet = async (e) => {
        e.preventDefault();
        try {
            await api.post('/sweets', newSweet);
            setShowAddForm(false);
            setNewSweet({ name: '', category: '', price: '', quantity: '' });
            fetchSweets(keyword);
            showNotification("Menu Updated ✨", "New sweet added successfully.");
        } catch (err) {
            showNotification("Error", "Failed to add sweet.", "error");
        }
    };

    return (
        <div className="container-fluid p-0">
            {/* Navbar */}
            <nav className="navbar navbar-dark sticky-top shadow-lg py-3" style={{ background: 'var(--charcoal-black)', borderBottom: '3px solid var(--gold-dark)' }}>
                <div className="container">
                    <span className="navbar-brand d-flex align-items-center">
                        <span style={{ fontSize: '1.8rem' }}>🪔</span>
                        <div className="ms-3 lh-1">
                            <span className="d-block font-decorative text-warning fs-5">Royal</span>
                            <span className="fw-bold text-white text-uppercase" style={{ letterSpacing: '2px' }}>Sweet Shop</span>
                        </div>
                    </span>
                    <div className="d-flex align-items-center">
                        <span className="text-gold me-3 d-none d-md-inline">Welcome, {user?.sub}</span>
                        <button className="btn btn-outline-warning btn-sm" onClick={() => { authService.logout(); navigate('/login'); }}>LOGOUT</button>
                    </div>
                </div>
            </nav>

            <div className="container mt-5 pb-5">
                {/* Admin Add Button */}
                {isAdmin && (
                    <div className="d-flex justify-content-end mb-4">
                        <button className="btn btn-primary shadow" onClick={() => setShowAddForm(!showAddForm)}>
                            {showAddForm ? 'Close Form' : '+ Add Special Item'}
                        </button>
                    </div>
                )}

                {/* Add Form */}
                {showAddForm && (
                    <div className="premium-card p-4 mb-5" style={{ background: '#fffcf5' }}>
                        <h4 className="mb-4 text-center text-maroon">Create New Offering</h4>
                        <form onSubmit={handleAddSweet} className="row g-3">
                            <div className="col-md-3"><input className="form-control" placeholder="Name" required value={newSweet.name} onChange={e => setNewSweet({...newSweet, name: e.target.value})} /></div>
                            <div className="col-md-3"><input className="form-control" placeholder="Category" required value={newSweet.category} onChange={e => setNewSweet({...newSweet, category: e.target.value})} /></div>
                            <div className="col-md-2"><input type="number" className="form-control" placeholder="Price" required value={newSweet.price} onChange={e => setNewSweet({...newSweet, price: e.target.value})} /></div>
                            <div className="col-md-2"><input type="number" className="form-control" placeholder="Qty" required value={newSweet.quantity} onChange={e => setNewSweet({...newSweet, quantity: e.target.value})} /></div>
                            <div className="col-md-2"><button type="submit" className="btn btn-primary w-100">Publish</button></div>
                        </form>
                    </div>
                )}

                {/* Filter Bar */}
                <div className="premium-card p-3 mb-5 d-flex flex-wrap gap-3 justify-content-center bg-white">
                    <input className="form-control w-50" placeholder="Search our menu..." value={keyword} onChange={e => setKeyword(e.target.value)} />
                    <button className="btn btn-dark" onClick={() => fetchSweets(keyword)}>Search</button>
                    <button className="btn btn-outline-dark" onClick={() => sortSweets('asc')}>Price Low</button>
                    <button className="btn btn-outline-dark" onClick={() => sortSweets('desc')}>Price High</button>
                </div>

                {/* Sweet Grid */}
                <div className="row g-4">
                    {sweets.map((sweet) => (
                        <div key={sweet.id} className="col-md-4 col-lg-3">
                            <div className="premium-card h-100 d-flex flex-column text-center">
                                {/* IMAGE SECTION */}
                                <div style={{ height: '220px', background: '#f8f8f8', position: 'relative', overflow: 'hidden' }}>
                                    <img 
                                        src={getSweetImage(sweet.name)} 
                                        alt={sweet.name} 
                                        style={{ width: '100%', height: '100%', objectFit: 'cover', transition: 'transform 0.5s' }}
                                        className="hover-zoom"
                                    />
                                    <span className="badge bg-danger position-absolute top-0 start-0 m-2 shadow">{sweet.category}</span>
                                </div>
                                
                                <div className="card-body p-4 d-flex flex-column">
                                    <h5 className="fw-bold mb-1" style={{fontFamily: 'Playfair Display'}}>{sweet.name}</h5>
                                    <div className="mb-3"><span className="fs-5 fw-bold text-maroon">${sweet.price.toFixed(2)}</span></div>
                                    <p className="small text-muted mb-4">
                                        {sweet.quantity > 0 ? <span className="text-success">In Stock ({sweet.quantity})</span> : <span className="text-danger">Out of Stock</span>}
                                    </p>
                                    <div className="mt-auto">
                                        {isAdmin ? (
                                            <div className="btn-group w-100">
                                                <button className="btn btn-outline-dark btn-sm" onClick={() => { setActiveSweet(sweet); setModalType('RESTOCK'); setQuantityInput(1); }}>Restock</button>
                                                <button className="btn btn-outline-danger btn-sm" onClick={() => { setActiveSweet(sweet); setModalType('DELETE'); }}>Delete</button>
                                            </div>
                                        ) : (
                                            <button className="btn btn-primary w-100" disabled={sweet.quantity === 0} onClick={() => { setActiveSweet(sweet); setModalType('BUY'); setQuantityInput(1); }}>
                                                {sweet.quantity === 0 ? 'Unavailable' : 'Buy Now'}
                                            </button>
                                        )}
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>

            {/* 1. INPUT MODAL */}
            {activeSweet && modalType && (
                <div className="modal-overlay" onClick={closeInputModal}>
                    <div className="modal-premium" onClick={e => e.stopPropagation()}>
                        <h3 className="mb-2 text-maroon">
                            {modalType === 'BUY' && "Complete Your Purchase"}
                            {modalType === 'RESTOCK' && "Update Stock"}
                            {modalType === 'DELETE' && "Remove Item"}
                        </h3>
                        <p className="text-muted fst-italic mb-4">{activeSweet.name}</p>
                        
                        {modalType !== 'DELETE' ? (
                            <div className="mb-4">
                                <label className="fw-bold small text-uppercase mb-2">Quantity</label>
                                <input 
                                    type="number" 
                                    className="form-control form-control-lg text-center" 
                                    value={quantityInput} 
                                    onChange={e => setQuantityInput(e.target.value)} 
                                    min="1"
                                />
                                {modalType === 'BUY' && <p className="mt-2 text-gold">Total: ${(activeSweet.price * quantityInput).toFixed(2)}</p>}
                            </div>
                        ) : <p className="text-danger mb-4">This action cannot be undone.</p>}

                        <div className="d-flex gap-3">
                            <button className="btn btn-outline-dark flex-grow-1" onClick={closeInputModal}>Cancel</button>
                            <button className={`btn flex-grow-1 ${modalType === 'DELETE' ? 'btn-danger' : 'btn-primary'}`} onClick={confirmAction}>Confirm</button>
                        </div>
                    </div>
                </div>
            )}

            {/* 2. NOTIFICATION MODAL */}
            {notification.show && (
                <div className="modal-overlay">
                    <div className="modal-premium" style={{ border: notification.type === 'error' ? '2px solid red' : '2px solid var(--gold-dark)' }}>
                        <span className="modal-icon">{notification.type === 'error' ? '❌' : '✨'}</span>
                        <h3 className="mb-3" style={{ color: notification.type === 'error' ? 'red' : 'var(--royal-maroon)' }}>{notification.title}</h3>
                        <p className="text-muted mb-4">{notification.message}</p>
                        <button className="btn btn-primary w-100 py-2" onClick={closeAll}>Okay, Got it</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Home;