import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login"; // Check that Login.js has "export default Login"
import Register from "./pages/Register"; // Check that Register.js has "export default Register"
import Chat from "./pages/Chat"; // Check that Chat.js has "export default Chat"

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/chat" element={<Chat />} />
      </Routes>
    </Router>
  );
}

export default App;
