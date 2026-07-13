import React, { useState } from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

function App() {
    const [view, setView] = useState('book');

    const renderContent = () => {
        if (view === 'book') {
            return <BookDetails />;
        } else if (view === 'blog') {
            return <BlogDetails />;
        } else if (view === 'course') {
            return <CourseDetails />;
        }
        return null;
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>Blogger App</h1>
            <div style={{ marginBottom: '15px' }}>
                <button onClick={() => setView('book')} style={{ marginRight: '10px', fontWeight: view === 'book' ? 'bold' : 'normal' }}>Book Details</button>
                <button onClick={() => setView('blog')} style={{ marginRight: '10px', fontWeight: view === 'blog' ? 'bold' : 'normal' }}>Blog Details</button>
                <button onClick={() => setView('course')} style={{ fontWeight: view === 'course' ? 'bold' : 'normal' }}>Course Details</button>
            </div>
            {renderContent()}
        </div>
    );
}
export default App;
