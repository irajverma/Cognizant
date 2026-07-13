import React from 'react';
import { useParams, Link } from 'react-router-dom';
function TrainerDetail({ trainers }) {
    const { id } = useParams();
    const trainer = trainers.find(t => t.TrainerId.toString() === id);

    if (!trainer) {
        return (
            <div>
                <h3>Trainer not found!</h3>
                <Link to="/trainers">Back to List</Link>
            </div>
        );
    }

    return (
        <div style={{ border: '1px solid #ccc', padding: '15px', maxWidth: '400px', borderRadius: '5px' }}>
            <h3>Trainer Detail</h3>
            <p><strong>Trainer ID:</strong> {trainer.TrainerId}</p>
            <p><strong>Name:</strong> {trainer.Name}</p>
            <p><strong>Email:</strong> {trainer.Email}</p>
            <p><strong>Phone:</strong> {trainer.Phone}</p>
            <p><strong>Technology:</strong> {trainer.Technology}</p>
            <p><strong>Skills:</strong> {trainer.Skills}</p>
            <Link to="/trainers">Back to List</Link>
        </div>
    );
}
export default TrainerDetail;
