import React, { useState } from 'react';
import CurrencyConvertor from './CurrencyConvertor';

function App() {
    const [counter, setCounter] = useState(0);

    const handleIncrement = () => {
        setCounter(prev => prev + 1);
        alert("Counter Incremented! Hello from React!");
    };

    const handleDecrement = () => {
        setCounter(prev => prev - 1);
    };

    const handleSayWelcome = (msg) => {
        alert("Greeting: " + msg);
    };

    const handlePress = (e) => {
        alert("Synthetic Event target ID: " + e.target.id + " -> I was clicked");
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>Event Handling Examples</h1>
            <h3>Counter: {counter}</h3>
            <button onClick={handleIncrement} style={{ marginRight: '10px' }}>Increment</button>
            <button onClick={handleDecrement}>Decrement</button>
            
            <br/><br/>
            <button onClick={() => handleSayWelcome('welcome')}>Say Welcome</button>
            
            <br/><br/>
            <button id="syntheticBtn" onClick={handlePress}>Press (Synthetic Event)</button>

            <CurrencyConvertor />
        </div>
    );
}
export default App;
