import React from 'react'
import OrderCard from './OrderCard'

const Orders = () => {
    return (
        <div className='min-h-screen flex items-center flex-col justify-start bg-gray-100 px-4"'>
            <h1 className='text-xl text-center py-7 font-semibold '>My Orders</h1>
            {/* <div className='space-y-5 w-full lg:w-1/2'> */}
            <div className="w-full max-w-2xl space-y-5">
            {
                [1,1,1].map((item)=> <OrderCard />)
            }</div>
        </div>
    )
}

export default Orders
