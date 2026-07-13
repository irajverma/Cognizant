import React from 'react';
function ListofPlayers() {
    const players = [
        { name: 'Virat Kohli', score: 110 },
        { name: 'Rohit Sharma', score: 85 },
        { name: 'KL Rahul', score: 45 },
        { name: 'Shikhar Dhawan', score: 55 },
        { name: 'MS Dhoni', score: 95 },
        { name: 'Hardik Pandya', score: 65 },
        { name: 'Rishabh Pant', score: 72 },
        { name: 'Ravindra Jadeja', score: 80 },
        { name: 'Jasprit Bumrah', score: 20 },
        { name: 'Mohammed Shami', score: 15 },
        { name: 'Yuzvendra Chahal', score: 5 }
    ];

    const below70 = players.filter(p => p.score < 70);

    return (
        <div>
            <h3>All Players</h3>
            <ul>
                {players.map((p, idx) => (
                    <li key={idx}>{p.name} - Score: {p.score}</li>
                ))}
            </ul>
            <h3>Players Scoring Below 70</h3>
            <ul>
                {below70.map((p, idx) => (
                    <li key={idx}>{p.name} - Score: {p.score}</li>
                ))}
            </ul>
        </div>
    );
}
export default ListofPlayers;
