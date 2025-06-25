import React from 'react'
import HomeIcon from '@mui/icons-material/Home';
import { Button, Card } from '@mui/material';

const AddressCard = ({ item, showButton , handleSelectAddress}) => {
    return (
        <Card sx={{
            display: 'flex',
            gap: 2,
            width: '335px', // "w-58" is 58 * 4 = 232px
            padding: 2,     // p-5 = 1.25rem = 20px (theme.spacing(2.5))
        }}>
            <HomeIcon />
            <div className='space-y-3 text-gray-500'>
                <h1 className='font-semibold text-lg text-white'>Home</h1>
                <p>Mumbai,new shiv build,gokuldham,maharashtra</p>
                {showButton && (
                    <Button variant='contained' fullWidth
                        onClick={() => handleSelectAddress(item)}>select</Button>)
                }
            </div>
        </Card>
    )
}

export default AddressCard
