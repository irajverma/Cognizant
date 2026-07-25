import React, { Component } from 'react';

class Getuser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: true,
            error: null
        };
    }

    componentDidMount() {
        fetch('https://api.randomuser.me/')
            .then(res => {
                if (!res.ok) throw new Error('API request failed');
                return res.json();
            })
            .then(data => {
                if (data.results && data.results.length > 0) {
                    this.setState({ user: data.results[0], loading: false });
                } else {
                    throw new Error('No user data returned');
                }
            })
            .catch(err => {
                this.setState({ error: err.message, loading: false });
            });
    }

    render() {
        const { user, loading, error } = this.state;

        if (loading) return <div>Loading user details...</div>;
        if (error) return <div style={{ color: 'red' }}>Error: {error}</div>;
        if (!user) return <div>No user found.</div>;

        return (
            <div style={{ border: '2px solid purple', padding: '20px', width: '300px', borderRadius: '8px', textAlign: 'center', backgroundColor: '#fdfcfe' }}>
                <h3>User Details</h3>
                <img 
                    src={user.picture.large} 
                    alt="User" 
                    style={{ borderRadius: '50%', width: '120px', height: '120px', border: '3px solid purple' }} 
                />
                <p style={{ fontSize: '1.2em', fontWeight: 'bold', marginTop: '10px' }}>
                    {user.name.title} {user.name.first} {user.name.last}
                </p>
                <p style={{ color: 'gray' }}>{user.email}</p>
            </div>
        );
    }
}
export default Getuser;
