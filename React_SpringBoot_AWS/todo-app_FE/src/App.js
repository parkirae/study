import './App.css';
import Todo from './Todo';
import React, { useState, useEffect } from 'react';
import {
  Container,
  List,
  Paper,
  Grid,
  Button,
  AppBar,
  Toolbar,
  Typography,
} from '@mui/material';
import AddTodo from './AddTodo';
import { call, signout } from './Service/ApiService';

function App() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    call('/todo', 'GET', null).then((response) => setItems(response.data));
  }, []);

  const addItem = (item) => {
    call('/todo', 'POST', item).then((response) => setItems(response.data));
  };

  const deleteItem = (item) => {
    call('/todo', 'DELETE', item).then((response) => setItems(response.data));
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

  let navigationBar = (
    <AppBar position="static">
      <Toolbar>
        <Grid justifyContent="space-between" container>
          <Grid item>
            <Typography variant="h6">오늘의 할 일</Typography>
          </Grid>
          <Grid item>
            <Button color="inherit" raised onClick={signout}>
              로그아웃
            </Button>
          </Grid>
        </Grid>
      </Toolbar>
    </AppBar>
  );

  return (
    <div className="App">
      {navigationBar}
      <Container maxWidth="md">
        <AddTodo />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );
}

export default App;
