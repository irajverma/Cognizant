import React, { Component } from 'react';
class Cart extends Component {
    render() {
        return (
            <div style={{ border: '1px solid #ccc', margin: '5px', padding: '10px', width: '200px', display: 'inline-block' }}>
                <h4>Item: {this.props.itemname}</h4>
                <p>Price: ${this.props.price}</p>
            </div>
        );
    }
}
export default Cart;
