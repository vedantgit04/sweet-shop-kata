import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import authService from '../services/authService';

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('ROLE_USER');
    const [error, setError] = useState('');
    const [showSuccess, setShowSuccess] = useState(false); // State for custom popup
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await authService.register(username, password, role);
            setShowSuccess(true); // Show custom modal instead of alert
        } catch (err) {
            setError('Username unavailable. Please try another.');
        }
    };

    const handleCloseSuccess = () => {
        setShowSuccess(false);
        navigate('/login');
    };

    return (
        <div className="container d-flex justify-content-center align-items-center min-vh-100">
            {/* Registration Card */}
            <div className="premium-card p-5" style={{ maxWidth: '450px', width: '100%', borderTop: '5px solid var(--gold-dark)' }}>
                <div className="text-center mb-4">
                    <span style={{ fontFamily: 'Great Vibes', fontSize: '2.5rem', color: 'var(--gold-dark)' }}>Join Us</span>
                    <h2 className="fw-bold" style={{ color: 'var(--royal-maroon)' }}>Register</h2>
                </div>

                {error && <div className="alert alert-danger rounded-0">{error}</div>}
                
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="fw-bold small text-muted text-uppercase">Username</label>
                        <input type="text" className="form-control" value={username} onChange={(e) => setUsername(e.target.value)} required />
                    </div>
                    <div className="mb-3">
                        <label className="fw-bold small text-muted text-uppercase">Password</label>
                        <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>
                    <div className="mb-4">
                        <label className="fw-bold small text-muted text-uppercase">Account Type</label>
                        <select className="form-select" value={role} onChange={(e) => setRole(e.target.value)}>
                            <option value="ROLE_USER">Customer (Connoisseur)</option>
                            <option value="ROLE_ADMIN">Shop Manager</option>
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary w-100 py-2">Create Account</button>
                </form>
                <div className="text-center mt-4">
                    <Link to="/login" className="text-decoration-none fw-bold" style={{ color: 'var(--gold-dark)' }}>Back to Login</Link>
                </div>
            </div>

            {/* CUSTOM SUCCESS MODAL */}
            {showSuccess && (
                <div className="modal-overlay">
                    <div className="modal-premium">
                        <span className="modal-icon">🎉</span>
                        <h3 className="mb-3" style={{ color: 'var(--royal-maroon)' }}>Welcome to the Family!</h3>
                        <p className="text-muted mb-4">Your account has been created successfully. You can now access our premium collection.</p>
                        <button className="btn btn-primary w-100 py-2" onClick={handleCloseSuccess}>
                            Proceed to Login
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Register;