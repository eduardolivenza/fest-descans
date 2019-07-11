import * as React from "react";
import IconButton from "@material-ui/core/IconButton";
import ViewList from "@material-ui/icons/viewList";
import ViewModule from "@material-ui/icons/viewModule";

export enum Layout { 
    Card, Table 
}

interface Props{
    layout: Layout;
    onChangeView: (layout: Layout) => void;
}

const getInfoFromLayout = (layout: Layout) => {
    let nextLayout: Layout;
    let icon;
    if (layout === Layout.Card) {
        nextLayout = Layout.Table;
        icon = <ViewList />;
    } else if (layout === Layout.Table) {
        nextLayout = Layout.Card;
        icon = <ViewModule />;
    }

    return {
        nextLayout,
        icon
    }
};

export const HotelCollectionViewSelectorComponent: React.FunctionComponent<Props> = (props) => {
    const { layout, onChangeView } = props;
    const {icon, nextLayout} = getInfoFromLayout(layout);
    const changeLayout = () => {
        onChangeView(nextLayout);
    }
    return (
        <>
            <IconButton color="primary" aria-label="View list of cards button"
                onClick={changeLayout}>
                {icon}
            </IconButton>
        </>
    );
};