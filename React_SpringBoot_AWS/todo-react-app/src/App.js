import './App.css';
import Todo from './Todo';
import React, { useState } from 'react';

function App() {
  const [items, setItems] = useState([
    {
      id: '0',
      title: 'Hello World 1',
      done: true,
    },
    {
      id: '1',
      title: 'Hello World 2',
      done: true,
    },
  ]);

  let todoItems =
    items.length > 0 && items.map((item) => <Todo item={item} key={item.id} />);

  return <div className="App">{todoItems}</div>;
}

export default App;
