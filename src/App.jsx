import { useEffect, useContext, lazy, Suspense } from "react";
import "./App.css";

const Home = lazy(() => import("./pages/Home/Home"));
const Beryle = lazy(() => import("./pages/Beryle/Beryle"));
const Myroom = lazy(() => import("./pages/Myroom/Myroom"));
/* const Profile = lazy(() => import("./pages/Profile")); */
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


import { Routes, Route, Navigate } from "react-router-dom";
import Loading from "./componets/Loading/Loading";
import Navbar from './components/Navbar/Navbar';
import NavbarWithoutUser from "./componets/Header/NavbarWithoutUser";

function App() {
  const { User, setUser } = useContext(AuthContext);
  useEffect(() => {
    const auth = getAuth();
    onAuthStateChanged(auth, (user) => {
      setUser(user);
      console.log(user);
    });
  }, []);

  return (
    <div>
      {User ? <Navbar></Navbar> : <NavbarWithoutUser></NavbarWithoutUser>}
      <Suspense replace fallback={<Loading />}>
        <Routes>
          <Route index path="/" element={User ? <Home /> : <Welcome />} />
          {User ? (
            <>
              <Route path="/home" element={<Home />} />
              <Route path="/Beryle" element={<Beryle />} />
              <Route path="/Myroom" element={<Myroom />} />
              <Route path="/Point" element={<Point />} />
              <Route path="/Questboard" element={<Questboard />} />
              <Route path="/AdminCreateQuest" element={<AdminCreateQuest />} />
              <Route path="/Setting" element={<Setting />} />

              <Route path="/AdminHome" element={<AdminHome />} />

            </>
          ) : null}

          <Route path="/AdminSignIn" element={<AdminSignIn />} />
          <Route path="/Admincontact" element={<Admincontact />} />
          <Route path="/AdminQuestboard" element={<AdminQuestboard />} />
          <Route path="/Userlist" element={<Userlist />} />

          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="*" element={<ErrorPage />} />
        </Routes>
      </Suspense>
    </div>
  );
}

export default App;