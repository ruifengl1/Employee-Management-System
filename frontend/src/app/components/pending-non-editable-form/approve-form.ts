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
  visaStatuses: []
};

export interface Document {
  comment: string;
  createDate: Date;
  documentType: string;
  id: number;
  path: string;
  title: string;
};

export interface Address {
  addressLine1: string | null;
  addressLine2: string | null;
  city: string | null;
  id: number | null;
  state: string | null;
  zipCode: number | null;
};

export interface Contact{
  alternatePhone: string;
  cellPhone: string;
  email: string;
  relationship: string;
  firstName: string;
  lastName: string;
};

