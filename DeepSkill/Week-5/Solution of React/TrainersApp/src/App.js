import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './Components/Home';
import TrainersList from './Components/TrainersList';
import TrainerDetail from './Components/TrainerDetail';
import { TrainersData } from './TrainersMock';

function App() {
    return (
        <Router>
            <div style={{ padding: '20px' }}>
                <nav style={{ marginBottom: '20px', borderBottom: '1px solid #eee', paddingBottom: '10px' }}>
                    <Link to="/" style={{ marginRight: '15px' }}>Home</Link>
                    <Link to="/trainers">Trainers List</Link>
                </nav>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/trainers" element={<TrainersList trainers={TrainersData} />} />
                    <Route path="/trainers/:id" element={<TrainerDetail trainers={TrainersData} />} />
                </Routes>
            </div>
        </Router>
    );
}
export default App;
