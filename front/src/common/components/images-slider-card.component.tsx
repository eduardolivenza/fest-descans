import * as React from "react";
import { Card, CardContent, CardHeader } from "@material-ui/core";
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
import { ProductEntityVm } from "core/dataModel/product-entity.vm";
import { makeStyles } from '@material-ui/core/styles';

interface Props {
    product: ProductEntityVm;
}

const useStyles = makeStyles(theme => ({
    card: {
        marginTop: theme.spacing(1),
    },
}));

export const ImagesSliderCard = (props: Props) => {

    const { product } = props;
    const classes = useStyles({});

    return (
        <>
            <Card className={classes.card}>
                <CardHeader title="Product images" titleTypographyProps={{color: "primary"}}/>
                <CardContent>
                    <AwesomeSlider style={{ marginBottom: '6vh', maxWidth: "31.25rem" }}>
                        {product.pictures.map(image => (
                            <div key={image} style={{ backgroundImage: `url(${image})`, backgroundColor: '#ffffff', backgroundRepeat: 'no-repeat', backgroundSize: 'contain', backgroundPosition: '50% 50%' }} />
                        ))}
                    </AwesomeSlider>
                </CardContent>
            </Card>
        </>
    )
}