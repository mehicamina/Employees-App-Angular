export interface IMenu {
    lable: string;
    icon: string;
    disabled: boolean;
}


export class AvailibilityM {
    transactionId: string;
    className: string;
    responseCode: number;
    responseDetail: string;
    payload: CalndarResponse[];
    recordsPerPage: number;
    page: number;
    numberOfPages: number;
    numberOfRecords: number;
    successful: boolean;
}

export class CalndarResponse {
    activityCode: string;
    validFrom: string;
    validTo: string;
    firstName: string;
    lastName: string;
}



