import * as React from "react";
import { useTranslation } from 'react-i18next';


export const DefaultComponent = () => {

    const { t } = useTranslation();

    return (
        <>
            <div>
                <h1>{t('title')}</h1>
            </div>
            <div>{t('description.part2')}</div>
            <div>{t('description.part3')}</div>
            <div>normal string</div>
        </>

    );
}