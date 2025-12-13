import api from './api';
import { jwtDecode } from 'jwt-decode';

const authService = {
    register: async (username, password, role) => {
        const response = await api.post('/auth/register', { username, password, role });
        return response.data;
    },

    login: async (username, password) => {
        const response = await api.post('/auth/login', { username, password });
        if (response.data.token) {
            localStorage.setItem('token', response.data.token);
        }
        return response.data;
    },

    logout: () => {
        localStorage.removeItem('token');
    },

    getCurrentUser: () => {
        const token = localStorage.getItem('token');
        if (token) {
            try {
                return jwtDecode(token);
            } catch (error) {
                return null;
            }
        }
        return null;
    },

    isAuthenticated: () => {
        return !!localStorage.getItem('token');
    }
};

export default authService;