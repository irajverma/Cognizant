import React, { useState } from 'react';

function Register() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({});

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = {};

        if (name.length < 5) {
            validationErrors.name = 'Name should have at least 5 characters';
        }
        if (!email.includes('@') || !email.includes('.')) {
            validationErrors.email = 'Email should contain @ and .';
        }
        if (password.length < 8) {
            validationErrors.password = 'Password should have at least 8 characters';
        }

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
        } else {
            setErrors({});
            alert('Registration successful!');
            setName('');
            setEmail('');
            setPassword('');
        }
    };

    return (
        <div style={{ padding: '20px', border: '1px solid #ccc', maxWidth: '400px', borderRadius: '5px' }}>
            <h2>Register Account</h2>
            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: '15px' }}>
                    <label style={{ display: 'block' }}>Name:</label>
                    <input 
                        type="text" 
                        value={name} 
                        onChange={(e) => setName(e.target.value)} 
                        style={{ width: '100%', padding: '8px' }}
                    />
                    {errors.name && <span style={{ color: 'red', fontSize: '0.85em' }}>{errors.name}</span>}
                </div>

                <div style={{ marginBottom: '15px' }}>
                    <label style={{ display: 'block' }}>Email:</label>
                    <input 
                        type="text" 
                        value={email} 
                        onChange={(e) => setEmail(e.target.value)} 
                        style={{ width: '100%', padding: '8px' }}
                    />
                    {errors.email && <span style={{ color: 'red', fontSize: '0.85em' }}>{errors.email}</span>}
                </div>

                <div style={{ marginBottom: '15px' }}>
                    <label style={{ display: 'block' }}>Password:</label>
                    <input 
                        type="password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        style={{ width: '100%', padding: '8px' }}
                    />
                    {errors.password && <span style={{ color: 'red', fontSize: '0.85em' }}>{errors.password}</span>}
                </div>

                <button type="submit" style={{ padding: '8px 15px', backgroundColor: '#007BFF', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
                    Submit
                </button>
            </form>
        </div>
    );
}
export default Register;
