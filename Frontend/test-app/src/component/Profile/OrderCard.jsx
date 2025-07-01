import { Button, Card } from '@mui/material'
import React from 'react'

const OrderCard = () => {
    return (
        // <Card className='flex justify-between items-center p-5'>
        <Card
            className="flex justify-between items-center p-5 w-full"
            sx={{
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                padding: 2,
            }}
        >
            <div className='flex items-center space-x-5'>
                <img className='h-16 w-16'
                src='https://cdn.pixabay.com/photo/2018/03/21/06/54/food-3245765_1280.jpg' alt='' />
                <div>
                    <p>Pizza</p>
                    <p>₹399</p>
                </div>
            </div>
            <div>
                <Button className='cursor-not-allowed'>completed</Button>
            </div>
        </Card>
    )
}

export default OrderCard
