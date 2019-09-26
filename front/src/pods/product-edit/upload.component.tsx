import React , {Fragment} from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';

const useStyles = makeStyles(theme => ({
    rightIcon: {
        marginLeft: theme.spacing(1),
    },
    uploadButton: {
        marginTop: theme.spacing(2), 
        marginRight: theme.spacing(2), 
        marginBottom: theme.spacing(2), 
    },
  }));

interface Props {
    onConfirmSubmit: () => void;
    onChangeFile: (file: File) => void;
}

const FileUpload = (props: Props) =>{
    
    const classes = useStyles({});
    const {onConfirmSubmit, onChangeFile} = props;
    
    const onChange = e => {
        onChangeFile(e.target.files[0])
    }

    const onSubmit = e => {
        e.preventDefault();
        onConfirmSubmit();
    }

    return(
        <div >
        <Fragment> 
            <div className="custom-file mb-4">
                <input type="file" onChange={onChange} className="custom-file-input" id="customFile"/>
            </div>
        </Fragment>
         <Button variant="contained" color="default" className={classes.uploadButton} onClick={onSubmit}>
            Upload
            <CloudUploadIcon className={classes.rightIcon} />
        </Button>
       </div>
    );
};

export default FileUpload;