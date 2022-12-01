import React from 'react';
import { Container, Grid, Typography, TextField, Button } from '@mui/material';
import { Link } from 'react-router-dom';
import { signin, socialLogin } from './Service/ApiService';

const Login = () => {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.target);
    const username = data.get('username');
    const password = data.get('password');
    // ApiService signin을 이용해 로그인합니다.
    signin({ username: username, password: password });
  };

  const handleSocialLogin = (provider) => {
    socialLogin(provider);
  };

  return (
    <Container component="main" maxWidth="xs" style={{ marginTop: '8%' }}>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <Typography component="h1" variant="h5">
            로그인
          </Typography>
        </Grid>
      </Grid>
      <form noValidate onSubmit={handleSubmit}>
        {' '}
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              id="username"
              label="아이디"
              name="username"
              autoComplete="username"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              variant="outlined"
              required
              fullWidth
              name="password"
              label="패스워드"
              type="password"
              id="password"
              autoComplete="current-password"
            />
          </Grid>
          <Grid item xs={12}>
            <Button type="submit" fullWidth variant="contained" color="primary">
              로그인
            </Button>
          </Grid>
          <Grid item xs={12}>
            <Button
              onClick={() => handleSocialLogin('github')}
              fullWidth
              variant="contained"
              style={{ backgroundColor: '#000' }}
            >
              깃허브로 로그인
            </Button>
          </Grid>
          <Grid item>
            <Link to="/signup" variant="body2">
              계정이 없다면 여기서 가입하세요 :)
            </Link>
          </Grid>
        </Grid>
      </form>
    </Container>
  );
};

export default Login;
