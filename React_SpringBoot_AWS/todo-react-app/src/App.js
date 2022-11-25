import './App.css';
import Todo from './Todo';
import React, { useState } from 'react';

function App() {
  const [item, setItem] = useState({
    id: '0',
    title: 'Hello World 1',
    done: true,
  });
  return (
    <div className="App">
      <Todo item={item} />
    </div>
  );
}

export default App;
