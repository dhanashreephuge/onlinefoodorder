import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from '@mui/material'
import React, { useEffect, useState } from 'react'
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import MenuCard from './MenuCard'; 
import { useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getRestaurantById, getRestaurantsCategory } from '../State/Restaurant/Action';
import {getMenuItemsByRestaurantId} from"../State/Menu/Action"

// const categories = [
//     "pizza",
//     "biryani",
//     "burger",
//     "chinese",
//     "cake"
// ];

const foodTypes = [
    { label: "All", value: "all" },
    { label: "Vegetarin only", value: "veg" },
    { label: "Non-Vegetarin", value: "non-veg" },
    { label: "Sesonal", value: "sesonal" }
];

const menu = [1,1,1,1,1]

const RestaurantDetails = () => {
    const [foodType, setFoodType] = useState("all")
     const navigate=useNavigate()
    const dispatch=useDispatch();
    const jwt=localStorage.getItem("jwt")
    const {auth,restaurant,menu} = useSelector(store=>store);
    const [selectedCategory,setSelectedCategory]=useState("");

    const {id,city}=useParams();

    const handleFilter = (e) => {
        setFoodType(e.target.value)
        console.log(e.target.value, e.target.name)
    };

    const handleFilterCategory = (e,value) => {
        setSelectedCategory(value)
        console.log(e.target.value, e.target.name,value);
    };

    console.log("restaurant",restaurant)

    useEffect(()=>{
        dispatch(getRestaurantById({jwt,restaurantId:id}));
        dispatch(getRestaurantsCategory({jwt,restaurantId:id}));
    },[]);

    useEffect(() =>{
         dispatch( getMenuItemsByRestaurantId({
            jwt,restaurantId:id,
            vegetarian:foodType==="vegetarian",
            nonveg:foodType==="non_vegetarian",
            seasonal:foodType==="seasonal",
            foodCategory:selectedCategory,
        }));
    },[selectedCategory,foodType])

    return (
        <div className='px-5 lg:px-20'>
            <section>
                <h3 className='text-gray-500 py-2 mt-10'>Home/India/Indian fast food/3</h3>
                <div>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <img className='w-full h-[40vh] object-cover'
                                src={restaurant.restaurant?.images[0]} alt='' />
                        </Grid>
                        <Grid item xs={12} lg={6}>
                            <img className='w-full h-[40vh] object-cover'
                                src={restaurant.restaurant?.images[1]} alt='' />
                        </Grid>
                        <Grid item xs={12} lg={6}>
                            <img className='w-full h-[40vh] object-cover'
                                src='https://media.istockphoto.com/id/1149712056/photo/modern-interior-design-of-restaurant-lounge-oriental-arabic-style-with-wire-mesh-ceiling-and.jpg?s=612x612&w=0&k=20&c=nlktc1PUyXIk0_S5X83CHhtkbyJ_sO1mMgZDcVMarG4=' alt='' />
                        </Grid>
                    </Grid>
                </div>

                <div className='pt-3 pb-5'>
                    <h1 className='text-4xl font-semibold'>{restaurant.restaurant?.name}</h1>
                    <p className='text-gray-500 mt-1'>{restaurant.restaurant?.discription}</p>
                    <div className='space-y-3 mt-3'>
                        <p className='text-gray-500 flex items-center gap-3'>
                            <LocationOnIcon />
                            <span>
                                Mumbai, Maharashtra
                            </span>
                        </p>
                        <p className='text-gray-500 flex items-center gap-3'>
                            <CalendarTodayIcon />
                            <span>
                                Mon-Sun: 9:00 AM - 11.00 PM (Today)
                            </span>
                        </p>
                    </div>

                </div>
            </section>

            <Divider />
            <section className='pt-[2rem] lg:flex relative'>
                <div className='space-y-10 lg:w-[20%] filter p-5 '>
                    <div className='box space-y-5 lg:sticky top-28'>
                        <div>
                            <Typography variant='h5' sx={{ paddingBottom: "1rem" }}>
                                Food Type
                            </Typography>

                            <FormControl className='py-10 space-y-5' component={"fieldset"}>
                                <RadioGroup onChange={handleFilter} name="food_type" value={foodType}>
                                    {foodTypes.map((item) => (
                                        <FormControlLabel
                                            key={item.value}
                                            value={item.value}
                                            control={<Radio />}
                                            label={item.label} />
                                    ))}
                                </RadioGroup>
                            </FormControl>
                        </div>

                        <Divider />
                        <div>
                            <Typography variant='h5' sx={{ paddingBottom: "1rem" }}>
                                Food Category
                            </Typography>

                            <FormControl className='py-10 space-y-5' component={"fieldset"}>
                                <RadioGroup onChange={handleFilterCategory} name="food_category" 
                                value={selectedCategory}
                                >
                                    {restaurant.categories.map((item) => (
                                        <FormControlLabel
                                            key={item}
                                            value={item.name}
                                            control={<Radio />}
                                            label={item.name} />
                                    ))}
                                </RadioGroup>
                            </FormControl>
                        </div>
                    </div>
                </div>
                <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                    {menu.menuItems.map((item) => <MenuCard item={item}/>)}
                </div>
            </section>
        </div>
    )
}

export default RestaurantDetails
