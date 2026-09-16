export interface ActionButton {
    label: string;
    action: string;
    icon?: string;
    img?: string;
    styles?: string;
    title?: string;
    disabled?: boolean;
}

export interface ColumnConfig {
    typeColum: typeColum;
    satusValue?: boolean;
    columValue?: string;
    actionButtons?: ActionButton[];
}

export enum typeColum {
    button = 'BUTTON',
    status = 'STATUS',
    string = 'STRING'
}