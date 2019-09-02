import * as React from "react";
import Typography from "@material-ui/core/Typography";
import Link from '@material-ui/core/Link';

export const Copyright = () => {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {'Copyright © '}
            <Link color="inherit" href="https://material-ui.com/">
                Fest Descans SL
        </Link>{' '}
            {new Date().getFullYear()}
            {'. Built with '}
            <Link color="inherit" href="https://material-ui.com/">
                Material-UI.
        </Link>
        </Typography>
    );
}