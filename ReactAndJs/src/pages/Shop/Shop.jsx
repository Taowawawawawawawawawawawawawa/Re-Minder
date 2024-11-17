// // import React, { useState,useEffect } from 'react';
// // import './Shop.css';
// // import Navbar from '../../components/Navbar/Navbar';
// // import Footer from '../../components/Footer/footer';


// // const items = [
// //   { id: 1, category: 'costume', name: 'หมวกคาวบอย', image: 'cowboy-hat.png', price: 359 },
// //   { id: 2, category: 'costume', name: 'หมวกหรูหรา', image: 'luxury-hat.png', price: 359 },
// //   { id: 3, category: 'costume', name: 'หมวกเอ็กซ์พลอเรอร์', image: 'explorer-hat.png', price: 359 },
// //   { id: 4, category: 'costume', name: 'ชุดแดง', image: 'red-outfit.png', price: 359 },
// //   { id: 5, category: 'costume', name: 'เนคไทสุดเท่', image: 'cool-tie.png', price: 359 },
// //   { id: 6, category: 'costume', name: 'โบว์แห่งความตาย', image: 'bow.png', price: 359 },
// //   { id: 7, category: 'costume', name: 'ผ้าพันคอ', image: 'scarf.png', price: 359 },
// //   { id: 8, category: 'costume', name: 'ผ้าพันคอน่ารัก', image: 'cute-scarf.png', price: 359 },
// //   { id: 9, category: 'costume', name: 'ปีก', image: 'wings.png', price: 359 },

// // ];

// // const Shop = () => {
// //   const [selectedSection, setSelectedSection] = useState('costume');
// //   const [selectedItem, setSelectedItem] = useState(null);

// //   const handleItemClick = (item) => {
// //     setSelectedItem(item);
// //     alert(`You selected: ${item.name}`);
// //   };
// //   /*ดึงAPI*/
// //   const [shops,setshops] = useState([]);

// //   useEffect(()=>{
// //     const fetchshop = async() => {
// //       const dataCostume = await fetch("http://localhost:8204/costumes/all");
// //       const costumeJson = await dataCostume.json();
// //       setshops(costumeJson);
// //     };
// //     fetchshop();
// //   },[]);

// //   return <><Navbar />
// //     <div className="shop-container">
// //       <div className="shop-tabs">
// //         <button
// //           className={selectedSection === 'costume' ? 'active' : ''}
// //           onClick={() => setSelectedSection('costume')}
// //         >
// //           costume
// //         </button>
// //         <button
// //           className={selectedSection === 'theme' ? 'active' : ''}
// //           onClick={() => setSelectedSection('theme')}
// //         >
// //           Theme
// //         </button>
// //       </div>
// //       {/* ดึงข้อมูลมาใส้ */}
// //       {shops.map((costumeData)=>{
// //                 console.log(shops);
// //             })} 
// //       <div className="shop-shelf">
// //         {items
// //           .filter(item => item.category === selectedSection)
// //           .map(item => (
// //             <div key={item.id} className="shop-item" onClick={() => handleItemClick(item)}>
// //               <img src={item.image} alt={item.name} />
// //               <div className="item-name">{item.name}</div>
// //               <div className="item-price">
// //                 {item.price} <span className="currency-icon">💎</span>
// //               </div>
// //             </div>
// //           ))}
// //       </div>

// //       <div className="shop-buttons">
// //         <button className="cancel-btn">ยกเลิก</button>
// //         <button className="save-btn">บันทึก</button>
// //       </div>
// //     </div>
// //     <Footer /></>
// // };

// // export default Shop;

// import React, { useState, useEffect } from 'react';
// import './Shop.css';
// import Navbar from '../../components/Navbar/Navbar';
// import Footer from '../../components/Footer/footer';

// const Shop = () => {
//   const [selectedSection, setSelectedSection] = useState('costume');
//   const [selectedItem, setSelectedItem] = useState(null);

//   const [shops, setShops] = useState([]);
//   const [error, setError] = useState(null); // For error handling

//   const handleItemClick = (item) => {
//     setSelectedItem(item);
//     alert(`You selected: ${item.costumeName}`);
//   };

//   useEffect(() => {
//     const fetchShop = async () => {
//       try {
//         const response = await fetch('http://localhost:8204/costumes/all');
//         if (!response.ok) {
//           throw new Error(`HTTP error! Status: ${response.status}`);
//         }
//         const data = await response.json();
//         setShops(data);
//       } catch (err) {
//         setError(err.message);
//       }
//     };

//     fetchShop();
//   }, []);

//   return (
//     <>
//       <Navbar />
//       <div className="shop-container">
//         <div className="shop-tabs">
//           <button
//             className={selectedSection === 'costume' ? 'active' : ''}
//             onClick={() => setSelectedSection('costume')}
//           >
//             Costume
//           </button>
//           <button
//             className={selectedSection === 'theme' ? 'active' : ''}
//             onClick={() => setSelectedSection('theme')}
//           >
//             Theme
//           </button>
//         </div>

//         {error && <div className="error">Error: {error}</div>}

//         <div className="shop-shelf">
//           {selectedSection === 'costume' &&
//             shops.map((costume) => (
//               <div
//                 key={costume.costumeId}
//                 className="shop-item"
//                 onClick={() => handleItemClick(costume)}
//               >
//                 <img src={costume.costumeFiles} alt={costume.costumeName} />
//                 <div className="item-name">{costume.costumeName}</div>
//                 <div className="item-price">
//                   {costume.price} <span className="currency-icon">💎</span>
//                 </div>
//               </div>
//             ))}
//           {/* Add logic for themes or other sections as needed */}
//         </div>

//         <div className="shop-buttons">
//           <button className="cancel-btn">Cancel</button>
//           <button className="save-btn">Save</button>
//         </div>
//       </div>
//       <Footer />
//     </>
//   );
// };

// export default Shop;

import React, { useState, useEffect } from 'react';
import './Shop.css';
import Navbar from '../../components/Navbar/Navbar';
import Footer from '../../components/Footer/footer';

const Shop = () => {
  const [selectedSection, setSelectedSection] = useState('costume'); // Switch between costume and theme
  const [costumes, setCostumes] = useState([]);
  const [themes, setThemes] = useState([]);
  const [error, setError] = useState(null); // For error handling

  const fetchCostumes = async () => {
    try {
      const response = await fetch('http://localhost:8204/costumes/all');
      if (!response.ok) {
        throw new Error(`Costume API error: ${response.status}`);
      }
      const data = await response.json();
      setCostumes(data);
    } catch (err) {
      setError(err.message);
    }
  };

  const fetchThemes = async () => {
    try {
      const response = await fetch('http://localhost:8204/themes/all');
      if (!response.ok) {
        throw new Error(`Theme API error: ${response.status}`);
      }
      const data = await response.json();
      setThemes(data);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    // Fetch both costumes and themes on component load
    fetchCostumes();
    fetchThemes();
  }, []);

  const handleItemClick = (item) => {
    alert(`You selected: ${item.costumeName || item.themeName}`);
  };

  return (
    <>
      <Navbar />
      <div className="shop-container">
        <div className="shop-tabs">
          <button
            className={selectedSection === 'costume' ? 'active' : ''}
            onClick={() => setSelectedSection('costume')}
          >
            Costume
          </button>
          <button
            className={selectedSection === 'theme' ? 'active' : ''}
            onClick={() => setSelectedSection('theme')}
          >
            Theme
          </button>
        </div>

        {error && <div className="error">Error: {error}</div>}

        <div className="shop-shelf">
          {/* Render costumes if the selected section is costume */}
          {selectedSection === 'costume' &&
            costumes.map((costume) => (
              <div
                key={costume.costumeId}
                className="shop-item"
                onClick={() => handleItemClick(costume)}
              >
                <img src={costume.costumeFiles} alt={costume.costumeName} />
                <div className="item-name">{costume.costumeName}</div>
                <div className="item-price">
                  {costume.price} <span className="currency-icon">💎</span>
                </div>
              </div>
            ))}

          {/* Render themes if the selected section is theme */}
          {selectedSection === 'theme' &&
            themes.map((theme) => (
              <div
                key={theme.themeId}
                className="shop-item"
                onClick={() => handleItemClick(theme)}
              >
                <img src={theme.frameSpriteArts} alt="Theme Frame" />
                <div className="item-name">{theme.themeId}</div>
                <div className="item-price">
                  {theme.price} <span className="currency-icon">💎</span>
                </div>
              </div>
            ))}
        </div>

        <div className="shop-buttons">
          <button className="cancel-btn">Cancel</button>
          <button className="save-btn">Save</button>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Shop;
