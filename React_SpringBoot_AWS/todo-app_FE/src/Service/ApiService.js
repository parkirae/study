import { API_BASE_URL } from '../api-config';

export function call(api, method, request) {
  let headers = new Headers({
    'Content-Type': 'application/json',
  });

  // local storage에서 ACCESS TOKEN을 가져옵니다.
  const accessToken = localStorage.getItem('ACCESS_TOKEN');
  if (accessToken && accessToken != null) {
    headers.append('Authorization', 'Bearer ' + accessToken);
  }

  let options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: method,
  };

  if (request) {
    // GET
    options.body = JSON.stringify(request);
  }
  return fetch(options.url, options)
    .then((response) => {
      if (response.status === 200) {
        return response.json();
      } else if (response.status === 403) {
        window.location.href = '/login';
      } else {
        Promise.reject(response);
        throw Error(response);
      }
    })
    .catch((error) => {
      console.log('http error');
      console.logo(error);
    });
}

export function signin(userDTO) {
  return call('/auth/signin', 'POST', userDTO).then((response) => {
    if (response.token) {
      // local Storage에 토큰을 저장합니다.
      localStorage.setItem('ACCESS_TOKEN', response.token);
      // 토큰이 존재하는 경우 Todo 화면으로 이동합니다.
      window.location.href = '/';
    }
  });
}
