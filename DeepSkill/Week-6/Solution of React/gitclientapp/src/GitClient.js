import React, { Component } from 'react';
import axios from 'axios';

class GitClient extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: 'techiesyed',
            repos: [],
            loading: false,
            error: null
        };
    }

    getRepositories = () => {
        this.setState({ loading: true, error: null, repos: [] });
        return axios.get(`https://api.github.com/users/${this.state.username}/repos`)
            .then(res => {
                const repoNames = res.data.map(repo => repo.name);
                this.setState({ repos: repoNames, loading: false });
                return repoNames;
            })
            .catch(err => {
                this.setState({ error: err.message, loading: false });
                throw err;
            });
    }

    componentDidMount() {
        this.getRepositories();
    }

    render() {
        const { repos, loading, error, username } = this.state;
        return (
            <div style={{ padding: '15px', border: '1px solid #bbb', borderRadius: '5px', maxWidth: '400px' }}>
                <h3>GitHub Repositories for: {username}</h3>
                {loading && <p>Loading repositories...</p>}
                {error && <p style={{ color: 'red' }}>Error: {error}</p>}
                <ul>
                    {repos.map((name, index) => (
                        <li key={index}>{name}</li>
                    ))}
                </ul>
            </div>
        );
    }
}
export default GitClient;
