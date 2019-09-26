import React , {Fragment} from 'react';

interface Props {
    onUploadSubmit: (file: File) => void;
}

const FileUpload = (props: Props) =>{

    const {onUploadSubmit} = props;
    const [file, setFile] = React.useState();
   

    const onChange = e => {
        setFile(e.target.files[0])
    }

    const onSubmit = e => {
        e.preventDefault();
        onUploadSubmit(file);
    }

    return(
        <Fragment>
            <form onSubmit={onSubmit}>
                <div className="custom-file mb-4">
                    <input type="file" onChange={onChange} className="custom-file-input" id="customFile"/>
                </div>
                <input type="submit" value="Upload" className="btn btn-primary btn-block mt-4"/>
            </form>
        </Fragment>
    );
};

export default FileUpload;