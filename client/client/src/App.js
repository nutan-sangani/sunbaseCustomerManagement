import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Login from "./pages/Login";
import Home from "./pages/Home";
import GetCustomer from "./pages/GetCustomer";
import AddCustomer from "./pages/AddCustomer";
import UpdateCustomer from "./pages/UpdateCustomer";
import { ToastContainer } from "react-toastify";

function App() {
  return (
    <div className="App">
      <ToastContainer />
      <Router>
        <Routes>
          <Route
            path="/"
            element={
              <div>
                <Home />
              </div>
            }
          />
          <Route
            path="/login"
            element={
              <div>
                <Login />
              </div>
            }
          />
          <Route
            path="/addCustomer"
            element={
              <div>
                <AddCustomer />
              </div>
            }
          />
          <Route
            path="/getCustomer"
            element={
              <div>
                <GetCustomer />
              </div>
            }
          />

          <Route path="/updateCustomer/:id" element={
            <div>
              <UpdateCustomer/>
            </div>
          } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
