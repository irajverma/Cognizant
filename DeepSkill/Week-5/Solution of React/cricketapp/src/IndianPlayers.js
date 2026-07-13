import React from 'react';
function IndianPlayers() {
    const playersList = ['Virat', 'Rohit', 'KL Rahul', 'Dhawan', 'MS Dhoni', 'Hardik'];
    const [oddPlayer, evenPlayer, ...otherPlayers] = playersList;

    const T20players = ['Virat', 'Rohit', 'Hardik'];
    const RanjiTrophy = ['Shreyas', 'Ishand', 'Priyam'];
    const mergedPlayers = [...T20players, ...RanjiTrophy];

    return (
        <div>
            <h3>Destructured Players</h3>
            <p>Odd Team Player (First): {oddPlayer}</p>
            <p>Even Team Player (Second): {evenPlayer}</p>
            <p>Other Players: {otherPlayers.join(', ')}</p>

            <h3>Merged Players (T20 & Ranji)</h3>
            <ul>
                {mergedPlayers.map((player, idx) => (
                    <li key={idx}>{player}</li>
                ))}
            </ul>
        </div>
    );
}
export default IndianPlayers;
