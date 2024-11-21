import { Suspense, lazy } from "react";
import { Routes, Route } from 'react-router-dom';
import "./App.css";
import Loading from './components/Loading/Loading'; // A loading component for Suspense fallback
import AudioPlayer from "./components/AudioPlayer"; // Audio player for handling audio playback
import { AuthProvider } from './components/AuthProvider'; // Authentication provider

import Navbar from './components/Navbar/Navbar';

const Setting = lazy(() => import("./pages/Setting/Setting"));
const Home = lazy(() => import("./pages/Home/Home"));
const Beryle = lazy(() => import("./pages/Beryle/Beryle"));
const Myroom = lazy(() => import("./pages/Myroom/Myroom"));
const Questlog = lazy(() => import("./pages/Myroom/Questlog"));
const Diary = lazy(() => import("./pages/Myroom/Diary"));
const Wardrobe = lazy(() => import("./pages/Myroom/Wardrobe"));
const Shop = lazy(() => import("./pages/Shop/Shop"));
const Point = lazy(() => import("./pages/Point/Point"));
const SignIn = lazy(() => import("./pages/SignIn/SignIn"));
const SignUp = lazy(() => import("./pages/SignUp/SignUp"));
const Welcome = lazy(() => import("./pages/Welcome/Welcome"));
const ErrorPage = lazy(() => import("./pages/ErrorPage"));
const Questboard = lazy(() => import("./pages/Questboard/Questboard"));

// Admin components
const Userlist = lazy(() => import("./admin/Slimelist/Userlist"));
const AdminHome = lazy(() => import("./admin/Home/AdminHome"));
const AdminSignIn = lazy(() => import("./admin/AdminSignIn"));
const Admincontact = lazy(() => import("./admin/Contact/Admincontact"));
const AdminQuestboard = lazy(() => import("./admin/Quest/AdminQuestBoard"));
const AdminCreateQuest = lazy(() => import("./admin/CreateQuest/AdminCreateQuest"));
const AdminShop = lazy(() => import("./admin/Shop/AdminShop"));

function App() {
  return (
    <div>
      {/* Wrap everything with AuthProvider to ensure authentication context is available */}
      <AuthProvider>
        {/* Include AudioPlayer so that audio can change based on the route */}
        <AudioPlayer />

        {/* Suspense for lazy-loaded components */}
        <Suspense fallback={<Loading />}>
          <Routes>
            <Route path="/" element={<Welcome />} />
            <Route path="/home" element={<Home />} />
            <Route path="/beryle" element={<Beryle />} />
            <Route path="/myroom" element={<Myroom />} />
            <Route path="/Questlog" element={<Questlog />} />
            <Route path="/Diary" element={<Diary />} />
            <Route path="/Wardrobe" element={<Wardrobe />} />
            <Route path="/shop" element={<Shop />} />
            <Route path="/point" element={<Point />} />
            <Route path="/questboard" element={<Questboard />} />
            <Route path="/welcome" element={<Welcome />} />
            <Route path="/admincreatequest" element={<AdminCreateQuest />} />
            <Route path="/setting" element={<Setting />} />
            <Route path="/AdminShop" element={<AdminShop />} />
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
      </AuthProvider>
    </div>
  );
}

export default App;
