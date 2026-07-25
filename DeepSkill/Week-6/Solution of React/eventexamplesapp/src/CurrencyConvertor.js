import React, { useState } from 'react';
function CurrencyConvertor() {
    const [rupees, setRupees] = useState('');
    const [euro, setEuro] = useState(null);

    const handleConvert = (e) => {
        e.preventDefault();
        const converted = (parseFloat(rupees) / 90).toFixed(2);
        setEuro(converted);
    };

    return (
        <div style={{ marginTop: '20px', border: '1px solid #aaa', padding: '15px', maxWidth: '300px' }}>
            <h4>Currency Convertor (INR to EUR)</h4>
            <form onSubmit={handleConvert}>
                <label>Amount in Rupees: </label>
                <input 
                    type="number" 
                    value={rupees} 
                    onChange={(e) => setRupees(e.target.value)} 
                    required 
                />
                <br/><br/>
                <button type="submit">Convert</button>
            </form>
            {euro !== null && <p style={{ marginTop: '10px' }}>Equivalent in Euros: €{euro}</p>}
        </div>
    );
}
export default CurrencyConvertor;
