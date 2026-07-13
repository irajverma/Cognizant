import React, { Component } from 'react';
class CountPeople extends Component {
    constructor(props) {
        super(props);
        this.state = {
            entrycount: 0,
            exitcount: 0
        };
    }
    updateEntry = () => {
        this.setState(prevState => ({ entrycount: prevState.entrycount + 1 }));
    }
    updateExit = () => {
        this.setState(prevState => ({ exitcount: prevState.exitcount + 1 }));
    }
    render() {
        return (
            <div style={{ border: '2px solid blue', padding: '20px', maxWidth: '300px', borderRadius: '5px' }}>
                <h3>Mall People Counter</h3>
                <p>Entry Count: {this.state.entrycount}</p>
                <p>Exit Count: {this.state.exitcount}</p>
                <button onClick={this.updateEntry} style={{ marginRight: '10px', padding: '5px 10px' }}>Login (Enter)</button>
                <button onClick={this.updateExit} style={{ padding: '5px 10px' }}>Exit</button>
            </div>
        );
    }
}
export default CountPeople;
