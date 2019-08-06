import React, { Component } from 'react';
import { Modal, Button } from 'react-bootstrap';
import { render } from 'react-dom';

function DicardItemsModal(props)  {
   
    return (
        <Modal {...props} size="lg" aria-labelledby = "Discard Items in the card Modal" centered>
            <Modal.Header closeButton>
                <Modal.Title>
                    Dicard Items Confirmation
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <h4>Do you want to discard items that are added to the cart before going back</h4>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={props.discardItems}>Dicard</Button>
                <Button onClick={props.onHide}>Cancel</Button>
            </Modal.Footer>
        </Modal>
    );
   } 
   
   render(<DiscardItemsModal />);







