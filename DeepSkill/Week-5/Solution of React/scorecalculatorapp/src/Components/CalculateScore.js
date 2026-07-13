import React from 'react';
import '../Stylesheets/mystyle.css';
function CalculateScore({ Name, School, Total, goal }) {
  const average = goal > 0 ? ((Total / goal) * 100).toFixed(2) : 0;
  return (
    <div className="score-card">
      <h2>Student Details</h2>
      <p><strong>Name:</strong> {Name}</p>
      <p><strong>School:</strong> {School}</p>
      <p><strong>Total Marks:</strong> {Total}</p>
      <p><strong>Goal:</strong> {goal}</p>
      <p className="average"><strong>Average Score (Percentage):</strong> {average}%</p>
    </div>
  );
}
export default CalculateScore;
