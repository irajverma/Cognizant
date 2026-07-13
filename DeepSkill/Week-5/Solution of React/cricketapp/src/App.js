import React, { useState } from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

function App() {
    const [flag, setFlag] = useState(true);
    return (
        <div style={{ padding: '20px' }}>
            <h1>Cricket App</h1>
            <button onClick={() => setFlag(!flag)} style={{ marginBottom: '15px' }}>
                Toggle View (Current: {flag ? "ListofPlayers" : "IndianPlayers"})
            </button>
            {flag ? <ListofPlayers /> : <IndianPlayers />}
        </div>
    );
}
export default App;
