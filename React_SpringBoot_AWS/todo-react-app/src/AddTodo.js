import React, { useState } from 'react';

import { Button, Grid, TextField } from '@mui/material';

const AddTodo = (props) => {
  const [item, setItem] = useState({ title: '' });

  return (
    <Grid container style={{ marginTop: 20 }}>
      <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
        <TextField placeholder="Add Todo here" fullWidth />
      </Grid>
      <Grid xs={1} md={1} item>
        <Button
          fullWidth
          style={{ height: '100%' }}
          color="secondary"
          variant="outlined"
        >
          +
        </Button>
      </Grid>
    </Grid>
  );
};

export default AddTodo;
