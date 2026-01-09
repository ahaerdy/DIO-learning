import { useState } from 'react';

function SmartCounter() {
  const [quantity, upQuantity] = useState(1);

  return (
    <>
      <h1>{quantity}</h1>
      <button onClick={() => upQuantity(quantity + 1)}>Aumentar</button>
      <h3>{quantity+1}<br />
          {quantity+2}<br /> 
          {quantity+3}
      </h3>
    </>
  );
}

export default SmartCounter;
