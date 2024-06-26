import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { MemberSaveResponseDto } from 'src/app/dto/appUserFileDto/createMemberDto/MemberSaveResponseDto';
import { AppUserRequest } from 'src/app/dto/appUserFileDto/createMemberDto/appUserRequest';
import { MemberShowDto } from 'src/app/dto/appUserFileDto/getDto/memberShowDto';
import { PasswordRequestUpdateDto } from 'src/app/dto/appUserFileDto/updateMemberDto/passwordRequestUpdateDto';
import { UpdateMemberDto } from 'src/app/dto/appUserFileDto/updateMemberDto/updateMemberDto';
import { DataResponse } from 'src/app/dto/data.state.';
import { AssignRoleDto } from 'src/app/dto/roleDto/assignRoleDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  private url:string = 'http://localhost:9000/api/v1/member'
  
  getAllMembers(page: number, size: number):Observable<any>{
    return this.http.get<any>( `${this.url}?page=${page}&size=${size}`)
  }
  downloadModalXlsx():void{
    this.http.get('http://localhost:9000/api/v1/download/members', { responseType: 'blob' } ).subscribe(
      blob => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('Téléchargements','members.xlsx');
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
      }
    );
  }
  createMembersFromXlsx(memberFile: File | null ):Observable<DataResponse<MemberSaveResponseDto, any>>{
    if (!memberFile) {
      return throwError("File is null or undefined.");
    }
    const formData = new FormData();

    formData.append('memberFile', memberFile);

  return this.http.post<any>(`${this.url}/add/members`, formData);
  }

  createSingleUser(appUserRequest: AppUserRequest):Observable<DataResponse<MemberSaveResponseDto, any>>{
    
    return this.http.post<any>(`${this.url}/add/member`, appUserRequest);
  }

  getUserById(idMember: string): Observable<MemberShowDto>{
    return this.http.get<MemberShowDto>(`${this.url}/${idMember}`);
  }

  updateMemberProfile(appUserRequest: UpdateMemberDto):Observable<DataResponse<MemberSaveResponseDto, any>>{
    
     return this.http.put<any>(`${this.url}/update/profile`, appUserRequest);
  }

  updateMemberPassword(passwordRequestUpdateDto: PasswordRequestUpdateDto):Observable<DataResponse<MemberSaveResponseDto, any>>{
    
     return this.http.put<any>(`${this.url}/update/password`, passwordRequestUpdateDto);
  }

  assignRole(roleDto: AssignRoleDto): Observable<DataResponse<MemberShowDto, any>>{
    return this.http.put<any>(`${this.url}/assign/role`, roleDto);
  }
  searchMember(searchText: string, page: number, size: number): Observable<any>{
    return this.http.get<any>( `${this.url}/search?keyword=${searchText}&page=${page}&size=${size}`);

  }
}
