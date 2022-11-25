import React, { useState } from 'react';
import {
  ListItem,
  ListItemText,
  InputBase,
  Checkbox,
  ListItemSecondaryAction,
  IconButton,
} from '@mui/material';
import DeleteOutlined from '@mui/material/DeleteOutlined';

const Todo = (props) => {
  const [item, setItem] = useState(props.item);
  const [readOnly, setReadOnly] = useState(true);

  const turnOffReadOnly = () => {
    setReadOnly(false);
  };

  const turnOnreadOnly = (e) => {
    if (e.key === 'Enter') {
      setReadOnly(true);
    }
  };

  const deleteItem = props.deleteItem;

  const deleteEventHandler = () => {
    deleteItem(item);
  };

  return (
    <ListItem>
      <Checkbox checked={item.done} />
      <ListItemText>
        <InputBase
          inputProps={{ 'aria-label': 'naked', readOnly: readOnly }}
          onClick={turnOffReadOnly}
          type="text"
          id={item.id}
          name={item.id}
          value={item.title}
          multiline={true}
          fullWidth={true}
        />
      </ListItemText>
      <ListItemSecondaryAction>
        <IconButton aria-label="Delete Todo" onClick={deleteEventHandler}>
          <DeleteOutlined />
        </IconButton>
      </ListItemSecondaryAction>
    </ListItem>
  );
};

export default Todo;
