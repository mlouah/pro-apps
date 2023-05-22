import { IProject } from '@/shared/model/project.model';

export interface IClient {
  id?: number;
  name?: string;
  code?: string;
  clientLogoContentType?: string | null;
  clientLogo?: string | null;
  url?: string | null;
  dateCreation?: Date | null;
  dateModify?: Date | null;
  lastModifyBy?: string | null;
  createdBy?: string | null;
  notes?: string | null;
  projects?: IProject[] | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public clientLogoContentType?: string | null,
    public clientLogo?: string | null,
    public url?: string | null,
    public dateCreation?: Date | null,
    public dateModify?: Date | null,
    public lastModifyBy?: string | null,
    public createdBy?: string | null,
    public notes?: string | null,
    public projects?: IProject[] | null
  ) {}
}
