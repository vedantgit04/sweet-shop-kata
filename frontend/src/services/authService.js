import api from './api';
import { jwtDecode } from 'jwt-decode'; // Fix import if needed

const authService = {
    register: async (username, password, role) => {
        const response = await api.post('/auth/register', { username, password, role });
        return response.data;
    },

    login: async (username, password) => {
        const response = await api.post('/auth/login', { username, password });
        
        console.log("LOGIN RESPONSE:", response.data); // <--- DEBUG: Check the console!

        // The backend returns { "token": "..." }
        if (response.data.token) {
            localStorage.setItem('token', response.data.token);
            return response.data;
        } else {
            console.error("Token missing in response!");
            throw new Error("No token received");
        }
    },

    logout: () => {
        localStorage.removeItem('token');
    },

   // ... existing imports ...

    getCurrentUser: () => {
        const token = localStorage.getItem('token');
        if (token) {
            try {
                // Decode token to get user data (sub, role, exp)
                return jwtDecode(token);
            } catch (error) {
                return null;
            }
        }
        return null;
    },

    // NEW: Helper to check if user is Admin
    isAdmin: () => {
        const token = localStorage.getItem('token');
        if (token) {
            try {
                const decoded = jwtDecode(token);
                return decoded.role === 'ROLE_ADMIN';
            } catch (error) {
                return false;
            }
        }
        return false;
    },


    isAuthenticated: () => {
        return !!localStorage.getItem('token');
    }
};

export default authService;