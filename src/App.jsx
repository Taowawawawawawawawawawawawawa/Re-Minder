import { useEffect, useContext, lazy, Suspense } from "react";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Loading from "./components/Loading/Loading";
import Navbar from './components/Navbar/Navbar';
import NavbarWithoutUser from "./components/Header/NavbarWithoutUser";
import { AuthContext } from './context/AuthContext';
import { getAuth, onAuthStateChanged } from "firebase/auth"; // Ensure these imports are correctly added

const Home = lazy(() => import("./pages/Home/Home"));
const Beryle = lazy(() => import("./pages/Beryle/Beryle"));
const Myroom = lazy(() => import("./pages/Myroom/Myroom"));
const Point = lazy(() => import("./pages/Point/Point"));
const SignIn = lazy(() => import("./pages/SignIn"));
const SignUp = lazy(() => import("./pages/SignUp"));
const Welcome = lazy(() => import("./pages/Welcome"));
const ErrorPage = lazy(() => import("./pages/ErrorPage"));
const Questboard = lazy(() => import("./pages/Questboard/Questboard"));
const Setting = lazy(() => import("./pages/Setting/Setting"));
const Userlist = lazy(() => import("./admin/Userlist"));
const AdminHome = lazy(() => import("./admin/AdminHome"));
const AdminSignIn = lazy(() => import("./admin/AdminSignIn"));
const Admincontact = lazy(() => import("./admin/Admincontact"));
const AdminQuestboard = lazy(() => import("./admin/AdminQuestboard"));
const AdminCreateQuest = lazy(() => import("./admin/AdminCreateQuest"));

function App() {
  const { User, setUser } = useContext(AuthContext);

  useEffect(() => {
    const auth = getAuth();
    onAuthStateChanged(auth, (user) => {
      setUser(user);
      console.log(user);
    });
  }, [setUser]);

  return (
    <div>
      {User ? <Navbar /> : <NavbarWithoutUser />}
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route index path="/" element={User ? <Home /> : <Welcome />} />
          {User ? (
            <>
              <Route path="/home" element={<Home />} />
              <Route path="/beryle" element={<Beryle />} />
              <Route path="/myroom" element={<Myroom />} />
              <Route path="/point" element={<Point />} />
              <Route path="/questboard" element={<Questboard />} />
              <Route path="/admincreatequest" element={<AdminCreateQuest />} />
              <Route path="/setting" element={<Setting />} />
              <Route path="/adminhome" element={<AdminHome />} />
            </>
          ) : null}

          <Route path="/adminsignin" element={<AdminSignIn />} />
          <Route path="/admincontact" element={<Admincontact />} />
          <Route path="/adminquestboard" element={<AdminQuestboard />} />
          <Route path="/userlist" element={<Userlist />} />

          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="*" element={<ErrorPage />} />
        </Routes>
      </Suspense>
    </div>
  );
}

export default App;
