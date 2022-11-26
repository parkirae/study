import './App.css';
import Todo from './Todo';
import React, { useState, useEffect } from 'react';
import { Container, List, Paper } from '@mui/material';
import AddTodo from './AddTodo';
import { call } from './Service/ApiService';

function App() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    call('/todo', 'GET', null).then((response) => setItems(response.data));
  }, []);

  const addItem = (item) => {
    item.id = 'ID-' + items.length;
    item.done = false;
    setItems([...items, item]);
    console.log('items : ', items);
  };

  const deleteItem = (item) => {
    const newItems = items.filter((e) => e.id !== item.id);
    setItems([...newItems]);
  };

  const editItem = (item) => {
    call('/todo', 'PUT', item).then((response) => setItems(response.data));
  };

  let todoItems = items.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {items.map((item) => (
          <Todo
            item={item}
            key={item.id}
            editItem={editItem}
            deleteItem={deleteItem}
          />
        ))}
      </List>
    </Paper>
  );

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );
}

export default App;
