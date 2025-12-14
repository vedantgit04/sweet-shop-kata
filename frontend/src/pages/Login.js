import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import authService from '../services/authService';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await authService.login(username, password);
            navigate('/'); 
        } catch (err) {
            setError('Invalid credentials provided.');
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center min-vh-100">
            <div className="premium-card p-5" style={{ maxWidth: '450px', width: '100%', borderTop: '5px solid var(--gold-dark)' }}>
                <div className="text-center mb-4">
                    {/* UPDATED: Matches the "Join Us" style exactly */}
                    <span style={{ fontFamily: 'Great Vibes', fontSize: '2.5rem', color: 'var(--gold-dark)', display: 'block', marginBottom: '0.5rem' }}>
                        Namaste
                    </span>
                    <h2 className="fw-bold" style={{ color: 'var(--royal-maroon)' }}>Sign In</h2>
                    <div style={{ height: '2px', width: '60px', background: 'var(--gold-dark)', margin: '10px auto' }}></div>
                </div>
                
                {error && <div className="alert alert-danger text-center border-0 rounded-0">{error}</div>}
                
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="form-label text-uppercase fw-bold small text-muted" style={{letterSpacing: '1px'}}>Username</label>
                        <input
                            type="text"
                            className="form-control form-control-lg rounded-0"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="form-label text-uppercase fw-bold small text-muted" style={{letterSpacing: '1px'}}>Password</label>
                        <input
                            type="password"
                            className="form-control form-control-lg rounded-0"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100 btn-lg rounded-0 py-3">Enter Shop</button>
                </form>
                <div className="text-center mt-4">
                    <p className="mb-0 text-muted">New Customer? <Link to="/register" style={{ color: 'var(--gold-dark)', fontWeight: 'bold' }}>Register Here</Link></p>
                </div>
            </div>
        </div>
    );
};

export default Login;