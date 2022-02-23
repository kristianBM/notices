
import { BrowserRouter, Routes, Route } from "react-router-dom"
import NoticeRegister from './pages/NoticeRegister'
import WriterRegister from './pages/WriterRegister'
import Home from './pages/Home'
import Navbar from "./components/Navbar";

function App() {

  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/new_notice' element={<NoticeRegister />} />
        <Route path='/new_writer' element={<WriterRegister />} />
      </Routes>
    </BrowserRouter>
  );

}

export default App;