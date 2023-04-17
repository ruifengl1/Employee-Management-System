export interface Profile {
    addresses: Address[];
    alternatePhone: string;
    cellPhone: string;
    contacts: Contact[];
    dob: Date;
    driverLicense: string;
    driverLicenseExpiration: string;
    email: string;
    endDate: Date;
    firstName: string;
    gender: "male" | "female" | "non-binary";
    houseID: number;
    id: number;
    lastName: string;
    middleName: string;
    personalDocuments: Document[]; 
    preferredName: string;
    ssn: string;
    startDate: Date;
    userId: number;
    visaStatuses: Visa[]
  };

  export const defaultProfile = () => {
    const profile : Profile = {
    firstName: '',
    middleName: '',
    lastName: '',
    preferredName: '',
    email: '',
    ssn: '',
    dob: new Date(),
    gender: "male",
    addresses: [],
    contacts: [],
    personalDocuments: [], 
    visaStatuses: [],
    alternatePhone: '',
    cellPhone: '',
    driverLicense: '',
    driverLicenseExpiration: '',
    id: -1,
    startDate: new Date(),
    endDate: new Date(),
    houseID: -1,
    userId: 0,
  }
  return profile;
}
  export interface Document {
    comment: string;
    createDate: Date;
    documentType: string;
    id: number;
    path: string;
    title: string;
  }

  export interface Visa {
    id?: number | null;
    visaType: 'OPT Recepit' | 'OPT EAD' | 'I-983' | 'I-20' | 'OPT STEM Receipt' | 'OPT STEM EAD' | null;
    activeFlag: string;
    startDate: Date | null;
    endDate: Date | null;
    lastModificationDate: Date | null;
  }

  export const defaultVisa = () => {
    const visa : Visa = {
      id: null,
      visaType: null,
      activeFlag: "APPROVED",
      startDate: null,
      endDate: null,
      lastModificationDate: null
    }
    return visa;
  }

  export interface Address {
    addressLine1: string | null;
    addressLine2: string | null;
    city: string | null;
    id: number | null;
    state: string | null;
    zipCode: number | null;
  }

  export const defaultAddress = () => {
    const address : Address = {
      addressLine1: null,
      addressLine2: null,
      city: null,
      id: null,
      state: null,
      zipCode: null
    }
    return address;
  }

  export interface Contact {
    firstName: string | null;
    lastName: string | null;
    email: string | null;
    cellPhone: string | null;
    alternatePhone: string | null;
    relationship: string | null;
  }

  export const defaultContact = () => {
    const contact : Contact = {
      firstName: null,
      lastName: null,
      email: null,
      cellPhone: null,
      alternatePhone: null,
      relationship: null
    }
    return contact;
  }