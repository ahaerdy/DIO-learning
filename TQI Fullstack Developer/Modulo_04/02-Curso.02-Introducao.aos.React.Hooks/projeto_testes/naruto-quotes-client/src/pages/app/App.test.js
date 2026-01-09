import { render, screen } from '@testing-library/react';
import { App } from './App';

test('renders the app with a button, a quote and a button', () => {
  render(<App />);

  const buttonEL = screen.getByRole('button');
  const imageEL = screen.getByRole('img');
  const textEL = screen.getByRole('p');

  expect(buttonEL).toBeInTheDocument();
  expect(imageEL).toBeInTheDocument();
  expect(textEL).toBeInTheDocument();
});
