import React, { useState } from 'react';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const flights = [
        { id: 1, flightName: "Air India AI-101", route: "Delhi - Mumbai", price: 5000 },
        { id: 2, flightName: "Indigo 6E-202", route: "Bangalore - Chennai", price: 3500 },
        { id: 3, flightName: "SpiceJet SG-303", route: "Kolkata - Hyderabad", price: 4200 }
    ];

    return (
        <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
            <h1>Flight Ticket Booking Portal</h1>
            <div style={{ marginBottom: '20px' }}>
                {isLoggedIn ? (
                    <button onClick={() => setIsLoggedIn(false)}>Logout</button>
                ) : (
                    <button onClick={() => setIsLoggedIn(true)}>Login</button>
                )}
            </div>

            {!isLoggedIn ? (
                <div>
                    <h2>Guest Page - Browse Flights</h2>
                    <table border="1" cellPadding="10">
                        <thead>
                            <tr>
                                <th>Flight Name</th>
                                <th>Route</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            {flights.map(f => (
                                <tr key={f.id}>
                                    <td>{f.flightName}</td>
                                    <td>{f.route}</td>
                                    <td>INR {f.price}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    <p style={{ color: 'orange', fontWeight: 'bold' }}>* Please login to book tickets.</p>
                </div>
            ) : (
                <div>
                    <h2>User Page - Book Tickets</h2>
                    <h3>Available Flights for Booking:</h3>
                    <ul>
                        {flights.map(f => (
                            <li key={f.id} style={{ marginBottom: '15px' }}>
                                <strong>{f.flightName}</strong> ({f.route}) - INR {f.price}
                                <button style={{ marginLeft: '10px' }} onClick={() => alert(`Ticket booked successfully for ${f.flightName}!`)}>
                                    Book Now
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}
export default App;
