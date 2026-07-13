import React, { Component } from 'react';
class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null
    };
  }
  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts');
        }
        return response.json();
      })
      .then(data => {
        this.setState({ posts: data.slice(0, 10) }); // display first 10
      })
      .catch(error => {
        this.setState({ error: error.message });
      });
  }
  componentDidMount() {
    this.loadPosts();
  }
  componentDidCatch(error, info) {
    alert("An error occurred in component: " + error.message);
    this.setState({ error: error.message });
  }
  render() {
    if (this.state.error) {
      return <div style={{ color: 'red' }}>Error: {this.state.error}</div>;
    }
    return (
      <div>
        <h2>Post Listings</h2>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ borderBottom: '1px solid #ccc', margin: '10px 0', paddingBottom: '10px' }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}
export default Posts;
