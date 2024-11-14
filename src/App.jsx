import { Suspense, lazy } from "react";
import { Routes, Route } from 'react-router-dom';
import "./App.css";
import Loading from './components/Loading/Loading'; // A loading component for Suspense fallback

import Navbar from './components/Navbar/Navbar';
import NavbarWithoutUser from "./components/Header/NavbarWithoutUser";

// Lazy load components
const Setting = lazy(() => import("./pages/Setting/Setting"));
const Home = lazy(() => import("./pages/Home/Home"));
const Beryle = lazy(() => import("./pages/Beryle/Beryle"));
const Myroom = lazy(() => import("./pages/Myroom/Myroom"));
const Point = lazy(() => import("./pages/Point/Point"));
const SignIn = lazy(() => import("./pages/SignIn/SignIn"));
const SignUp = lazy(() => import("./pages/SignUp/SignUp"));
const Welcome = lazy(() => import("./pages/Welcome/Welcome"));
const ErrorPage = lazy(() => import("./pages/ErrorPage"));
const Questboard = lazy(() => import("./pages/Questboard/Questboard"));

// Admin components
const Userlist = lazy(() => import("./admin/Userlist"));
const AdminHome = lazy(() => import("./admin/AdminHome"));
const AdminSignIn = lazy(() => import("./admin/AdminSignIn"));
const Admincontact = lazy(() => import("./admin/Admincontact"));
const AdminQuestboard = lazy(() => import("./admin/AdminQuestboard"));
const AdminCreateQuest = lazy(() => import("./admin/AdminCreateQuest"));

function App() {
  return (
    <div>
      {/* {User ? <Navbar /> : <NavbarWithoutUser />} */}

      {/* Wrap Routes in Suspense with a fallback */}
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route path="/" element={<Welcome />} />
          <Route path="/home" element={<Home />} />
          <Route path="/beryle" element={<Beryle />} />
          <Route path="/myroom" element={<Myroom />} />
          <Route path="/point" element={<Point />} />
          <Route path="/questboard" element={<Questboard />} />
          <Route path="/welcome" element={<Welcome />} />
          <Route path="/admincreatequest" element={<AdminCreateQuest />} />
          <Route path="/setting" element={<Setting />} />
          <Route path="/adminhome" element={<AdminHome />} />
          <Route path="/adminsignin" element={<AdminSignIn />} />
          <Route path="/admincontact" element={<Admincontact />} />
          <Route path="/adminquestboard" element={<AdminQuestboard />} />
          <Route path="/userlist" element={<Userlist />} />
          <Route path="*" element={<ErrorPage />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
        </Routes>
      </Suspense>
    </div>
  );
}

export default App;