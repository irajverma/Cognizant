import React, { useState } from 'react';

function ComplaintRegister() {
    const [employeeName, setEmployeeName] = useState('');
    const [complaint, setComplaint] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const refNumber = 'REF-' + Math.floor(100000 + Math.random() * 900000);
        alert(`Thanks ${employeeName}!\nYour complaint has been registered.\nReference Number: ${refNumber}`);
        setEmployeeName('');
        setComplaint('');
    };

    return (
        <div style={{ padding: '20px', border: '1px solid #bbb', maxWidth: '400px', borderRadius: '5px' }}>
            <h2>Raise a Complaint</h2>
            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: '15px' }}>
                    <label style={{ display: 'block', marginBottom: '5px' }}>Employee Name:</label>
                    <input 
                        type="text" 
                        value={employeeName} 
                        onChange={(e) => setEmployeeName(e.target.value)} 
                        style={{ width: '100%', padding: '8px' }}
                        required 
                    />
                </div>
                <div style={{ marginBottom: '15px' }}>
                    <label style={{ display: 'block', marginBottom: '5px' }}>Complaint Description:</label>
                    <textarea 
                        value={complaint} 
                        onChange={(e) => setComplaint(e.target.value)} 
                        style={{ width: '100%', padding: '8px', height: '100px' }}
                        required 
                    />
                </div>
                <button type="submit" style={{ padding: '8px 15px', backgroundColor: '#e91e63', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
                    Submit Complaint
                </button>
            </form>
        </div>
    );
}
export default ComplaintRegister;
